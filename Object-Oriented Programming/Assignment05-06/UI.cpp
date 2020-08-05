#include "UI.h"

#include <string>
#include<fstream>
#include<iostream>
using namespace std;

UI::UI(Controller * ctrl)
{
	this->ctrl = ctrl;
}

void UI::printMenu()
{
	cout << endl;
	cout << "Adopt a cat!" << endl;
	cout << "1. Manage Cat List." << endl;
	cout << "2. Manage Adoption List" << endl;
	cout << "0. Exit" << endl;
}

void UI::printRepositoryMenu()
{
	cout << endl;
	cout << "You are in administrator mode now." << endl;
	cout << "1. Add a cat." << endl;
	cout << "2. Remove a cat." << endl;
	cout << "3. Update a cat." << endl;
	cout << "4. Display all cats." << endl;
	cout << "5. Load Data From File." << endl;
	cout << "6. Save The Modified Data To a File." << endl;
	cout << "0. Exit" << endl;
}

void UI::printAdoptionMenu()
{
	cout << endl;
	cout << "You are now in user mode." << endl;
	cout << "1. Next." << endl;
	cout << "2. Adopt this cat." << endl;
	cout << "3. See all the cats of a given breed, having an age less then a given number." << endl;
	cout << "4. See your adoption list." << endl;
	cout << "5. Display your List." << endl;
	cout << "0. Exit" << endl;
}

void UI::addCatToList()
{
	cout << "Give the breed of the cat:";
	string breed;
	cin >> breed;
	cout << "Give the name of the cat:";
	string name;
	cin >> name;
	cout << "Give the age of the cat:";
	int age;
	cin >> age;
	cout << "Give the link to the photo of the cat:";
	string source;
	cin >> source;

	if (this->ctrl->addCatToRepo(breed, name, age, source))
		cout << "Cat added successfully! " << endl;
	else
		cout << "The cat could not be added to our shelter." << endl;
}


void UI::addCatToAdopList()
{
	this->ctrl->addCatToAdoptionList(this->ctrl->getCrtCat());
}

void UI::removeCatFromList()
{
	cout << "Give the name of the cat you want to remove:" << endl;
	string name;
	cin >> name;
	if (this->ctrl->removeCatFromRepo(name))
		cout << "Cat successfully removed! " << endl;
	else
		cout << "The cat could not be removed! " << endl;
}

void UI::updateCatFromList()
{
	cout << "Give the name of the cat whose information you want to update:" << endl;
	string name;
	cin >> name;
	Cat c = this->ctrl->findByName2(name);
	if (c.getName() == "")
		cout << "There is no cat with that name! " << endl;
	else
	{
		cout << "Give the new age of the cat:" << endl;
		int age;
		cin >> age;
		cout << "Give the new link to the photo of the cat:" << endl;
		string source;
		cin >> source;
		if (this->ctrl->updateCatFromRepo(c.getBreed(), c.getName(), age, source))
			cout << "Update was successful!" << endl;
		else
			cout << "We could not update the information!" << endl;
	}
}

void UI::breedAndAgeUI() {
	int a;
	cout << "Give the breed you search for:" << endl;
	string breed;
	cin >> breed;
	cout << "Give the limit age you search for:" << endl;
	int age;
	cin >> age;
	vector<Cat> c = this->ctrl->breedAndAge2(breed, age);
	if (c.size() == 0)
	{
		cout << "No such cats in the shelter! " << endl;
		return;
	}
	for (int i = 0; i < c.size(); i++)
	{
		Cat current = c[i];
		this->printCatInfo(current);
		current.open();
		cout << "Do you want to adopt this cat? "  << endl;
		cout << "1. Yes" << endl;
		cout << "2. No" << endl;
		this->ctrl->configAdoptionList();
		cin >> a;
		if (a == 1) {
			this->ctrl->addCatToAdoptionList(current);
		}
		if (a == 0)
			break;
	}
}

void UI::displayAllCats()
{
	vector<Cat> c = this->ctrl->getRepo()->getCats();
	
	if (c.size()==0)
	{
		cout << "No cats in the shelter! " << endl;
		return;
	}
	for (int i = 0; i < c.size(); i++)
	{
		Cat current = c[i];
		this->printCatInfo(current);
	}
}

/*void UI::displayAdoptionList()
{
	vector<Cat> c = this->ctrl->getAdoptionList()->getCats();
	if (c.size() == 0)
	{
		cout << "No cats in the adoption list! " << endl;
		return;
	}
	for (int i = 0; i < c.size(); i++)
	{
		Cat current = c[i];
		this->printCatInfo(current);
	}
}*/



void UI::printCatInfo(const Cat& c)
{
	cout << "Name: " << c.getName() << endl;
	cout << "Breed: " << c.getBreed() << endl;
	cout << "Age: " << c.getAge() << endl;
	//cout << "Photo: " << c.getSource() << endl;
	cout << endl;
}

void UI::loadFromFile(std::string x) {
	std::ifstream infile(x);


	Cat cat;
	while (infile >> cat)
	{
		this->ctrl->getRepo()->addCat(cat);
	}
	infile.close();
}

void UI::viewAdoptionList()
{
	vector<Cat> a = this->ctrl->getAdoptionList()->getCats();
	if (a.size() == 0)
	{
		cout << "No cats in your adoption list yet!" << endl;
		return;
	}
	for (int i = 0; i < a.size(); i++) {
		this->printCatInfo(a[i]);
	}

}

void UI::loadToFile(std::string x)
{
	std::ofstream outFile(x);
	std::vector<Cat> cats = this->ctrl->getRepo()->getCats();


	for (auto &c : cats)
		outFile << c << std::endl;

	outFile.close();
}

void UI::run()
{
	while (true) {
		UI::printMenu();
		int command = 0;
		cout << "Give a command:";
		cin >> command;
		if (command == 0)
			break;

		if (command == 1)
		{
			while (true)
			{
				UI::printRepositoryMenu();
				int command2 = 0;
				cout << ">> ";
				cin >> command2;
				if (command2 == 0)
					break;
				std::string file;
				switch (command2)
				{
				case 1:
					this->addCatToList();
					break;
				case 2:
					this->removeCatFromList();
					break;
				case 3:
					this->updateCatFromList();
					break;
				case 4:
					this->displayAllCats();
					break;
				case 5:
					cout << "Give a file: ";
					cin >> file;
					this->loadFromFile(file);
					break;
				case 6:
					std::string f;
					cout << "Give the file where you want to save the data: ";
					cin >> f;
					this->loadToFile(f);
					break;
				}
			}
		}
		if (command == 2)
		{
			this->ctrl->configAdoptionList();
			this->ctrl->openCatList();
			this->printCatInfo(this->ctrl->getCrtCat());
			while (true)
			{
				UI::printAdoptionMenu();
				int command3 = 0;
				cout << ">> ";
				cin >> command3;
				if (command3 == 0)
					break;

				switch (command3)
				{
				case 2:
					this->addCatToAdopList();
					break;
				case 1:
					this->ctrl->nextCat();
					this->printCatInfo(this->ctrl->getCrtCat());
					break;
				case 3:
					this->breedAndAgeUI();
					break;
				case 4:
					this->viewAdoptionList();
					break;
				case 5:
					this->ctrl->display();
					break;
				}
			}
		}
	}
}