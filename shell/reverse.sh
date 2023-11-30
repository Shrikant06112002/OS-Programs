#!/bin/bash

echo -n "Enter a number: "
read num

echo "Reversed number:"
echo $num | rev

#!/bin/bash

echo -n "Enter a number: "
read num

reverse=0

while [ $num -gt 0 ]; do
    remainder=$((num % 10))
    reverse=$((reverse * 10 + remainder))
    num=$((num / 10))
done

echo "Reversed number: $reverse"
