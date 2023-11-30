#!/bin/bash

echo -n "Enter the file path: "
read filepath

if [ -e "$filepath" ]; then
    echo "File type: $(file -b $filepath)"
    echo "Permissions: $(ls -l $filepath | awk '{print $1}')"
else
    echo "File not found!"
fi

