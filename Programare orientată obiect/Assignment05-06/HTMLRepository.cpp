#include "HTMLRepo.h"
#include <fstream>
#include <string>
#include <algorithm>
#include <Windows.h>
#include <shellapi.h>
#include<iostream>
void HTMLRepository::loadToFile()
{


	std::ofstream outFile(file);
	std::vector<Cat> cats = this->getCats();
	outFile << "<!DOCTYPE html>" << endl;
	outFile << "<html>" << endl;
	outFile << "<head><title>    Adoption List    </title></head>" << endl;
	outFile << "<body style=\"background-color:white;\"><table border=\"3\">" << endl;



	outFile << "<tr>" << endl;
	outFile << "<td>Breed</td>" << endl;
	outFile << "<td>Name</td>" << endl;
	outFile << "<td>Age</td>" << endl;
	outFile << "<td>Source</td>" << endl;
	outFile << "</tr>" << endl;



	for (const auto& cat : cats) {
		outFile << "<tr>" << endl;
		outFile << "<td>" << cat.getBreed() << "</td>" << endl;
		outFile << "<td>" << cat.getName() << "</td>" << endl;
		outFile << "<td>" << cat.getAge() << "</td>" << endl;
		outFile << "<td>" << cat.getSource() << "</td>" << endl;
		outFile << "</tr>" << endl;
	}



	outFile << "</table>" << endl;
	outFile << "</body>" << endl;
	outFile << "</html>" << endl;
	outFile.close();
}

HTMLRepository::~HTMLRepository(){}

HTMLRepository::HTMLRepository(const std::string & file) : FileRepository(file) {
	std::ofstream ofs;

	ofs.open(file, std::ofstream::out | std::ofstream::trunc);

	ofs.close();
}

void HTMLRepository::displayPets()
{
	this->loadToFile();

	ShellExecuteA(NULL, NULL, "chrome.exe", "cats.html", NULL, SW_SHOWMAXIMIZED);
	system("PAUSE");
}