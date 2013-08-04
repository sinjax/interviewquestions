def permutations(s):
	_permutations(s,"")

def _permutations(i, constructing):
	# print "in:",i
	if len(i) == 1:
		print constructing + i
	for index in range(len(i)):
		_permutations(i[0:index] + i[index+1:],constructing + i[index])

def combinations(s):
	_combinations(s,"")
	pass

def _combinations(s,c):
	if(len(s) == 1):
		c+= s
		print c
		return
	for x in range(len(s)):
		print c + s[x]
		_combinations(s[x+1:],c+s[x])
print "== perms == "
permutations("tha")
print "== combs =="
combinations("tha")