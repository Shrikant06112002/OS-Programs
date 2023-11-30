#!/bin/bash

factorial() {
    if [ $1 -eq 0 ] || [ $1 -eq 1 ]; then
        echo 1
    else
        echo $(( $1 * $(factorial $(( $1 - 1 ))) ))
    fi
}

echo -n "Enter a number: "
read num

result=$(factorial $num)
echo "Factorial of $num is: $result"

#!/bin/bash

factorial() {
    result=1
    for ((i=1; i<=$1; i++)); do
        result=$((result * i))
    done
    echo $result
}

echo -n "Enter a number: "
read num

result=$(factorial $num)
echo "Factorial of $num is: $result"
