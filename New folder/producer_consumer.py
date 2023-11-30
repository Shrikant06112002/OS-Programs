import threading
import queue
import time

shared_queue = queue.Queue(maxsize=10)

num_items = 20


def producer():
    for i in range(num_items):
        item = f"Item {i}"
        shared_queue.put(item)
        print(f"Produced: {item}")
        time.sleep(0.1)


def consumer():
    while True:
        item = shared_queue.get()
        if item is None:
            break
        print(f"Consumed: {item}")
        shared_queue.task_done()


# Create producer and consumer threads
producer_thread = threading.Thread(target=producer)
consumer_thread = threading.Thread(target=consumer)

producer_thread.start()
consumer_thread.start()

producer_thread.join()

shared_queue.put(None)
consumer_thread.join()

print("All items produced and consumed.")
