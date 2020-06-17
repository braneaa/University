#pragma once
#include "Utility.h"
#include <string>
class Ethnologist
{

private:

	std::string name, sector;

public:
	Ethnologist();

	Ethnologist(std::string name, std::string sector) : name(name), sector(sector) {};

	std::string getName() { return name; }
	std::string getSector() { return sector; }
	void setName(std::string x) { name = x; }
	void setSector(std::string x) { sector = x; }
	std::string toString() {
		std::string str;
		str += name + ";" + sector + "\n";
		return str;
	}

	friend std::istream& operator>>(std::istream& stream, Ethnologist& eth);

	~Ethnologist();
};

