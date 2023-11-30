#!/bin/bash

echo -n "Enter the number of terms in the Fibonacci series: "
read n

a=0
b=1
echo "Fibonacci Series:"
echo -n "$a $b "

for ((i=2; i<n; i++))
do
    c=$((a + b))
    echo -n "$c "
    a=$b
    b=$c
done

echo
