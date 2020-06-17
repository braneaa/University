#pragma once
#include <string>
#include "Utility.h"

class Building
{

private:
	int id;
	std::string description;
	int year;
	std::string sector;
	int xCoord, yCoord;

public:
	Building();
	
	Building(int id, std::string description, int year, std::string sector, int xCoord, int yCoord) :id(id), description(description), year(year), sector(sector), xCoord(xCoord), yCoord(yCoord) {}

	int getId() { return id; }
	std::string getDescription() { return description; }
	int getYear() { return year; }
	std::string getBSector() { return sector; }
	int getXCoord() { return xCoord; }
	int getYCoord() { return yCoord; }

	void setId(int x) { id = x; }
	void setDescription(std::string x) { description = x; }
	void setYear(int x) { year = x; }
	void setBSector(std::string x) { sector = x; }
	void setXCoord(int x) { xCoord = x; }
	void setYCoord(int x) { yCoord = x; }

	std::string toString() {
		std::string str;
		str += std::to_string(id) + ";" + description + ";" + std::to_string(year) + ";" + sector + ";" + std::to_string(xCoord) + ";" + std::to_string(yCoord) + '\n';
		return str;
	}
	
	friend std::istream& operator>>(std::istream& stream, Building& b);

	~Building();
};

