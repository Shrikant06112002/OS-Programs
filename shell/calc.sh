#!/bin/bash

echo "Enter two numbers:"
read num1
read num2

echo "Choose an operation:"
echo "1. Addition"
echo "2. Subtraction"
echo "3. Multiplication"
echo "4. Division"

read choice

case $choice in
    1) result=$((num1 + num2));;
    2) result=$((num1 - num2));;
    3) result=$((num1 * num2));;
    4) result=$((num1 / num2));;
    *) echo "Invalid choice"; exit;;
esac

echo "Result: $result"
