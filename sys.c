#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>  // Include <string.h> for strlen

int main() {
    // Process related system calls: fork and wait
    pid_t child_pid = fork();

    if (child_pid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (child_pid == 0) {
        // Child process
        printf("Child process with PID: %d\n", getpid());
        printf("Child process exiting.\n");
        exit(EXIT_SUCCESS);
    } else {
        // Parent process
        printf("Parent process with PID: %d\n", getpid());
        wait(NULL); // Wait for the child process to complete
    }

    // File related system calls: open, read, write, close
    int fd = open("example.txt", O_CREAT | O_WRONLY | O_TRUNC, S_IRUSR | S_IWUSR);
    if (fd == -1) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    const char *message = "Hello, System Calls!\n";
    ssize_t bytes_written = write(fd, message, strlen(message));
    if (bytes_written == -1) {
        perror("write");
        close(fd);
        exit(EXIT_FAILURE);
    }

    close(fd);

    fd = open("example.txt", O_RDONLY);
    if (fd == -1) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    char buffer[256];
    ssize_t bytes_read = read(fd, buffer, sizeof(buffer));
    if (bytes_read == -1) {
        perror("read");
        close(fd);
        exit(EXIT_FAILURE);
    }

    buffer[bytes_read] = '\0'; // Null-terminate the buffer
    printf("Read from file: %s", buffer);

    close(fd);

    // Protection related system call: chmod
    if (chmod("example.txt", S_IRUSR | S_IRGRP | S_IROTH) == -1) {
        perror("chmod");
        exit(EXIT_FAILURE);
    }

    printf("File protection changed.\n");

    return 0;
}

