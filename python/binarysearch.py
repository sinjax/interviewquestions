#!/usr/bin/env python

_NOT_IN_ARRAY = -1
_ARRAY_UNORDERED = -2

def binarySearch(arr,target):
	return _binarySearch(arr,0,len(arr)-1,target)
def _binarySearch(arr,lower,upper,target):
	if lower is upper:
		if arr[lower] is target:
			return lower
		else:
			return _NOT_IN_ARRAY
	
	if(arr[lower] > arr[upper]):
		_ARRAY_UNORDERED
	
	half = lower + ((upper - lower) / 2)
	
	
	if target is arr[half]:
		return half
	elif target < arr[half]:
		return _binarySearch(arr,lower,half,target)
	else:
		return _binarySearch(arr,half+1,upper,target)

def ibinarySearch(arr,target):
	return _iterativeBinarySearch(arr,target)
def _iterativeBinarySearch(arr,target):
	lower = 0
	upper = len(arr) - 1
	while 1:
		if lower is upper:
			if arr[lower] is target:
				return lower
			else:
				return _NOT_IN_ARRAY
		
		if(arr[lower] > arr[upper]):
			_ARRAY_UNORDERED

		half = lower + ((upper - lower) / 2)
		if target is arr[half]:
			return half
		elif target < arr[half]:
			upper = half
		else:
			lower = half + 1

import random

nElements = 1000000
a = [int(nElements * random.random()) for x in range(nElements)]
a.sort()
print a

ind = int(random.random()*nElements)
print "Looking for index: ",ind
# print "Found: ",binarySearch(a,a[ind])
print "Found: ",ibinarySearch(a,a[ind])