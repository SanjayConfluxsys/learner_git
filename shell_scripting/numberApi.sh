#!/bin/bash

# Log file name
LOG_FILE="numberTriviaLog.txt"

# creating function to fetch number trivia
fetch_number_trivia() {
    local number=$1
    response=$(curl -s "http://numbersapi.com/$number")

    # Checking if the API call was successful
    if [[ $? -ne 0 ]]; then
        echo "Error: Failed to fetch data from Numbers API." >> "$LOG_FILE"
        exit 1
    fi

    # Logging the trivia with a timestamp
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Trivia for $number: $response" >> "$LOG_FILE"

    # Displaying the trivia
    echo "Trivia for $number: $response"
}

# Main script
echo "Would you like to fetch trivia for a specific number or a random number? (specific/random)"
read -r choice

if [[ "$choice" == "specific" ]]; then
    echo "Enter a number:"
    read -r user_number
    fetch_number_trivia "$user_number"
elif [[ "$choice" == "random" ]]; then
    fetch_number_trivia "random"
else
    echo "Invalid choice! Please enter 'specific' or 'random'."
    exit 1
fi

