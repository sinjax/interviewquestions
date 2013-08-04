#!/usr/bin/env python
import permutations as p

phoneDict = {
	1:""   ,2:"abc",3:"def",
	4:"ghi",5:"jkl",6:"mno",
	7:"prs",8:"tuv",9:"wxy",
	0:""
}

def _getCharKey(key,place):
	return phoneDict[key][place-1]

def phoneWords(number):
	if(len(number)!=7): return;
	constructed = []
	_phoneWords(number,0,"",constructed)
	return constructed

def _phoneWords(allNum,currentIndex,current,constructed):
	if currentIndex == len(allNum):
		constructed.append(current)
		return
	
	numberLetters = phoneDict[allNum[currentIndex]]
	for letter in numberLetters:
		_phoneWords(allNum,currentIndex+1,current + letter,constructed)

def _iphoneWords(numbers):
	constructed = []
	currentLetterIndex = [0 for x in range(len(numbers))]
	# while the first letter hasn't been through all permutations yet
	seenItAll = False
	while not seenItAll:
		currentString = ""
		for pointer in range(len(currentLetterIndex)):
			number = numbers[pointer]
			letter = currentLetterIndex[pointer]
			addFrom = phoneDict[number]
			if(len(addFrom) is not 0):
				currentString += addFrom[letter]
		constructed.append(currentString)
		# step forward 1, start at the end
		correctingIndex = len(currentLetterIndex) - 1
		while 1:
			currentLetterIndex[correctingIndex]+=1
			if(currentLetterIndex[correctingIndex] >= len(phoneDict[numbers[correctingIndex]])):
				if(correctingIndex == 0): # this is our stopping case!
					seenItAll = True
					break
				else:
					currentLetterIndex[correctingIndex] = 0
					correctingIndex-=1
			else:
				break
	return constructed

# print phoneWords([8,6,6,2,6,6,5])
print _iphoneWords([1,1,1,3,1,2])