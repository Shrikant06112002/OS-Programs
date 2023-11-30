#!/bin/bash

# Check if exactly two command-line arguments are provided
if [ $# -ne 2 ]; then
    echo "Usage: $0 <num1> <num2>"
    exit 1
fi

# Perform addition
sum=$(( $1 + $2 ))

# Display the sum
echo "Sum of $1 and $2 is: $sum"
