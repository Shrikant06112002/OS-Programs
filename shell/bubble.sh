#!/bin/bash



echo "Enter the size of Array"

read s

declare -a arr

echo "Enter the element in Array"

for ((i=0;i<s;i++));do

	read ele

	arr[$i]=$ele

done



#bubble sort

for ((i=0;i<s;i++));do

	for ((j=0;j<s-i-1;j++));do

		if [ ${arr[$j]} -gt ${arr[j+1]} ];then

			temp=${arr[j]}

			arr[$j]=${arr[j+1]}

			arr[$j+1]=$temp

		fi

	done

done

echo "element of array after reversing"



	echo "${arr[@]}"