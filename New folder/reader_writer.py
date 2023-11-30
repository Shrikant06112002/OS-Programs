import threading
import time

# Shared data
shared_data = 0

# Mutex for controlling access to shared data
mutex = threading.Lock()

# Semaphore for controlling read access
read_sem = threading.Semaphore(5)  # Allow up to 5 readers at a time

# Semaphore for controlling write access
write_sem = threading.Semaphore(1)  # Allow only 1 writer at a time

# Number of reads and writes
num_reads = 10
num_writes = 5


# Function for the reader
def reader():
    global shared_data
    for _ in range(num_reads):
        read_sem.acquire()  # Acquire read access
        mutex.acquire()  # Acquire mutex to access shared data
        print(f"Reader {threading.current_thread().name} read: {shared_data}")
        mutex.release()  # Release mutex
        read_sem.release()  # Release read access
        time.sleep(0.1)


# Function for the writer
def writer():
    global shared_data
    for _ in range(num_writes):
        write_sem.acquire()  # Acquire write access
        mutex.acquire()  # Acquire mutex to access shared data
        shared_data += 1
        print(f"Writer {threading.current_thread().name} wrote: {shared_data}")
        mutex.release()  # Release mutex
        write_sem.release()  # Release write access
        time.sleep(0.1)


# Create reader and writer threads
reader_threads = [threading.Thread(target=reader, name=f"Reader-{i}") for i in range(3)]
writer_threads = [threading.Thread(target=writer, name=f"Writer-{i}") for i in range(2)]

# Start the threads
for thread in reader_threads + writer_threads:
    thread.start()

# Wait for all threads to finish
for thread in reader_threads + writer_threads:
    thread.join()

print("All readers and writers finished.")
