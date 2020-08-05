#include "Ethnologist.h"



Ethnologist::Ethnologist()
{
}

std::istream & operator>>(std::istream & stream, Ethnologist& eth)
{
	std::string input;
	getline(stream, input);
	Utils u{};
	std::vector< std::string> tokens = u.tokenize(input);
	if (tokens.size() != 2) {
		return stream;
	}
	eth.setName(tokens[0]);
	eth.setSector(tokens[1]);
	return stream;
}

Ethnologist::~Ethnologist()
{
}
