#pragma once

#include "Controller.h"


class UI {
private:
	Controller* ctrl;

public:
	UI(Controller* controller);
	void run();

private:

	void printMenu();
	void printRepositoryMenu();
	void loadFromFile(std::string file);
	void addCatToList();
	void removeCatFromList();
	void updateCatFromList();
	void displayAllCats();
	void printCatInfo(const Cat& c);
	void printAdoptionMenu();
	//void displayAdoptionList();
	void addCatToAdopList();
	void viewAdoptionList();
	void breedAndAgeUI();
	void loadToFile(std::string file);
	
};