#include "Cat.h"

#include <Windows.h>
#include <shellapi.h>
#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

using namespace std;

Cat::Cat(): breed(""), name(""), age(0), source("") {}

Cat::Cat(const string& breed, const string& name, const int& age, const string& source)
{
	this->breed = breed;
	this->name = name;
	this->age = age;
	this->source = source;
}


Cat::~Cat()
{
	
}


string Cat::getBreed() const
{
	return breed;
}
string Cat::getName() const
{
	return name;
}
int Cat::getAge() const
{
	return age;
}
string Cat::getSource() const
{
	return source;
}

void Cat::open()
{
	ShellExecute(NULL, NULL, "chrome.exe", this->getSource().c_str(), NULL, SW_SHOWMAXIMIZED);
}

std::ostream & operator<<(std::ostream & out, const Cat & cat)
{
	//c_str
	out << cat.getBreed().c_str() << "," << cat.getName().c_str() << "," << cat.getAge() << "," << cat.getSource().c_str(); 
	return out;
}

vector<string> tokenize(const string& str, char delimiter)
{
	vector <string> result;
	stringstream ss(str);
	string token;
	while (getline(ss, token, delimiter))
		result.push_back(token);

	return result;
}

std::istream & operator >> (std::istream & in, Cat & cat)
{
	string line;
	getline(in, line);
	if (line.size() == 0)
		return in;
	std::vector<string> t = tokenize(line, ',');
	if (t.size() != 4) 
		throw ValidationException("Sorry! the number of fields is not ok!");
	cat.breed = t[0];
	cat.name = t[1];
	cat.age = stoi(t[2]);
	cat.source = t[3];
	return in;
}



/*
string Cat::setBreed(string b)
{
	this->breed = b;
}
string Cat::setName(string n)
{
	this->name = n;
}
int Cat::setAge(int a)
{
	this->age = a;
}
string Cat::setSource(string s)
{
	this->source = s;
}
*/