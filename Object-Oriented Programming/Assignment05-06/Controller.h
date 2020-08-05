#pragma once

#include "Repo.h"
#include "AdoptionList.h"
#include"FileRepository.h"
class Controller
{
private:
	FileRepository* repo;
	FileRepository* adoptionList;
	AdoptionList aList;

public:
	Controller(FileRepository* r, FileRepository* a) : aList()
	{
		repo = r; adoptionList = a;
	};
	FileRepository* getRepo() const { return repo; }
	Repository* getAdoptionList() const { return adoptionList; }

	bool addCatToRepo(const string& breed, const string& name, const int& age, const string& source);

	bool removeCatFromRepo(const string& name);

	bool updateCatFromRepo(const string& breed, const string& name, const int& newAge, const string& newsource);

	Cat findByName2(string& name);

	void addCatToAdoptionList(const Cat& c);

	//void openLink();

	Cat getCrtCat();

	std::vector<Cat> breedAndAge2(string& breed, int age);

	void openCatList();

	void nextCat();

	void configAdoptionList();

	void display();
};