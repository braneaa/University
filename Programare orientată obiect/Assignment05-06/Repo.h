#pragma once
#include <vector>
#include "Cat.h"
#include "DynamicVector.h"

class Repository {

private:
	std::vector<Cat> cats;

public:

	Repository() {}

	bool addCat(const Cat& c);

	Cat findByName(const string& name);

	std::vector<Cat> getCats() { return cats; }

	bool removeCat(const string& name);

	int findIndexOfCat(const Cat& c);

	std::vector<Cat> breedAndAge(const string& breed, const int age);

};

class RepositoryException : public ValidationException {
public:
	RepositoryException(std::string m) : ValidationException(m) {}
};