#include "Repo.h"

bool Repository::addCat(const Cat& c) {
	Cat x = this->findByName(c.getName());

	if (x.getName() != "")
		return false;
	this->cats.push_back(c);
	return true;
}

Cat Repository::findByName(const string& name) {
	//Cat* allCats = this->cats.getAllElems();

	for (int i = 0; i < this->cats.size(); i++)
	{
		Cat c = cats[i];
		if (c.getName() == name)
			return c;
	}
	return Cat{};
}

std::vector<Cat> Repository::breedAndAge(const string& breed, const int age) {
	//Cat* allCats = this->cats.getAllElems();
	std::vector<Cat> search = {};
	for (int i = 0; i < this->cats.size(); i++)
	{
		Cat c = cats[i];
		if (c.getBreed() == breed && c.getAge() < age)
			search.push_back(c);
	}
	return search;
}

bool Repository::removeCat(const string& name)
{
	Cat x = this->findByName(name);
	if (x.getName() == "")
		return false;
	for (int i = this->findIndexOfCat(x); i < this->cats.size()-1; i++) {
		cats[i] = cats[i + 1];
	}
	cats.pop_back();
	return true;
}

int Repository::findIndexOfCat(const Cat& c)
{
	for (int i = 0; i < this->cats.size(); i++)
		if (this->cats[i].getName() == c.getName())
			return i;
	return -1;
}
