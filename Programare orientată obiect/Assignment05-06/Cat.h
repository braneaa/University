#pragma once
#include <iostream>
#include <string.h>

using namespace std;

class Cat
{
private:
	string breed;
	string name;
	int age;
	string source;

public:
	Cat();
	Cat(const string& breed, const string& name, const int& age, const string& source);
	~Cat();

	string getBreed() const;
	string getName() const;
	int getAge() const;
	string getSource() const;
	/*string setBreed(string b);
	string setName(string n);
	int setAge(int a);
	string setSource(string s);
	*/
	void open();

	friend std::ostream & operator <<(std::ostream & out, const Cat & cat);
	friend std::istream & operator >> (std::istream & in, Cat & cat);
};

class ValidationException : public std::exception {
private:
	std::string message;
public:
	ValidationException(std::string message) : message(message) {}

	const char* what()
	{
		return message.c_str();
	}
};



