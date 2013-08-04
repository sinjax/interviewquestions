#!/usr/bin/env python

def combinations(s):
	outArr = []
	_combinations(s,"",outArr)
	return outArr
def permutations(s,stopping=-1):
	if stopping is -1: stopping = len(s)
	outArr = []
	_permutations(s,"",outArr,stopping)
	return outArr

def _permutations(s,current,outArr,stopping=0):
	if(len(current) == stopping):
		outArr.append(current)
		return
	
	for x in range(len(s)):
		_permutations(s[:x] + s[x+1:],current + s[x],outArr,stopping)


def _combinations(s,current,outArr):
	if(current is not ""):
		outArr.append(current)
	if(len(s) == 0):
		return
	for x in range(len(s)):
		_combinations(s[:x] + s[x+1:],current + s[x],outArr)

if __name__ == '__main__':	
	print permutations("bat")
	print permutations("bat",2)
	print combinations("bat")