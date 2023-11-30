#!/bin/bash

echo -n "Enter a string: "
read input

echo "Reversed string: $(echo $input | rev)"
#!/bin/bash

echo -n "Enter a string: "
read input

length=${#input}
reverse=""

for (( i=$length-1; i>=0; i-- )); do
    reverse="$reverse${input:$i:1}"
done

echo "Reversed string: $reverse"

