#include <stdio.h>
#include <string.h>

void reverseString(char input[], int start, int end){
	char swap;
	while(end > start){
		swap = input[start];
		input[start] = input[end];
		input[end] = swap;
		start++;
		end--;
	}
}

void reverseWords(char input[]){
	int len = strlen(input);
	reverseString(input,0,len-1);
	int wordEnd = 0;
	int wordStart = 0;
	while(wordEnd < len){
		if(input[wordEnd] == ' '){
			// reverse the string between wordStart and wordEnd
			reverseString(input, wordStart, wordEnd-1);
			wordStart = wordEnd+1;
		}
		wordEnd++;
	}
	reverseString(input, wordStart, wordEnd-1);
}

int main (int argc, const char * argv[]) {
	char input[] = "Do or do not, there is no try.";
	
	reverseWords(input);
	printf("%s\n",input);
	return 0;
}
