letter="b"
s1="bathandbutts"
s2="superbatbutts"
good=""
#we need to keep track of the second letter in order to know which is longest but we got mad and quit
for i in range(0, len(s1)):
        if s1[i]==letter:
                StringS1=s1[i:len(s1)]
                break
print StringS1

for j in range(0, len(s2)):
        if s2[j]==letter:
                StringS2=s2[j:len(s2)]
print StringS2

if len(StringS1)>=len(StringS2):
        length=len(StringS2)
else:
        length=len(StringS1)
print length

for y in range(0, length):
        if StringS1[y] == StringS2[y]:
                good+=StringS2[y]
        if StringS1[y] != StringS2[y]:
                
                
                                
print good



