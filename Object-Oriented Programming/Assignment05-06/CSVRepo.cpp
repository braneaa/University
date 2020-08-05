#include "CSVRepo.h"
#include <fstream>
#include<stdlib.h>

void CSVRepository::loadFromFile() {
	std::ifstream infile(file);
	if (!infile.is_open())
		throw RepositoryException("Cannot open file: " + file);
	Cat cat;
	while (infile >> cat)
	{
		this->addCat(cat);
	}
	infile.close();
}

void CSVRepository::loadToFile()
{
	std::ofstream outFile(file);
	std::vector<Cat> cats = this->getCats();
	for (auto&c : cats)
		outFile << c << std::endl;
	outFile.close();
}

CSVRepository::CSVRepository(const std::string & file) : FileRepository(file) {
	this->loadFromFile();
}

CSVRepository::~CSVRepository()
{
	this->loadToFile();
}

void CSVRepository::displayPets()
{
	this->loadToFile();
	std::string s = "notepad \"" + file + "\"";
	system(s.c_str());
}