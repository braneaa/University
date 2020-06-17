#include "Building.h"



Building::Building()
{
}


std::istream & operator>>(std::istream & stream, Building& b)
{
	std::string input;
	getline(stream, input);
	Utils u{};
	std::vector< std::string> tokens = u.tokenize(input);
	if (tokens.size() != 6) {
		return stream;
	}
	b.setId(std::stoi(tokens[0]));
	b.setDescription(tokens[1]);
	b.setYear(std::stoi(tokens[2]));
	b.setBSector(tokens[3]);
	b.setXCoord(std::stoi(tokens[4]));
	b.setYCoord(std::stoi(tokens[5]));
	return stream;
}

Building::~Building()
{
}
