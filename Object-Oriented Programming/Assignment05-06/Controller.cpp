#include "Controller.h"

bool Controller::addCatToRepo(const string& breed, const string& name, const int& age, const string& source)
{
	Cat c{ breed, name, age, source };
	return this->repo->addCat(c);
}

bool Controller::removeCatFromRepo(const string& name) 
{
	return this->repo->removeCat(name);
}

bool Controller::updateCatFromRepo(const string& breed, const string& name, const int& newAge, const string& newsource) 
{
	Cat c = this->repo->findByName(name);
	if (c.getName() == "")
		return false;

	this->removeCatFromRepo(name);
	this->addCatToRepo(breed, name, newAge, newsource);
}

void Controller::addCatToAdoptionList(const Cat& c)
{
	this->adoptionList->addCat(c);
}

Cat Controller::findByName2(string& name)
{
	Cat c = this->repo->findByName(name);
	return c;
}

std::vector<Cat> Controller::breedAndAge2(string& breed, int age) {
	return this->repo->breedAndAge(breed, age);
}

Cat Controller::getCrtCat()
{
	return this->aList.getCrt();
}

void Controller::openCatList()
{
	this->aList.open();
}

void Controller::nextCat()
{
	return this->aList.next();
}
void Controller::configAdoptionList()
{
	this->aList.isEmpty();
	std::vector<Cat> c = this->repo->getCats();
	for (int i = 0; i < c.size(); i++)
		this->aList.add(c[i]);
}

void Controller::display() {
	this->adoptionList->displayPets();
}

