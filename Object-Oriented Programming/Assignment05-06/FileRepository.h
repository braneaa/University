#pragma once
#include "Repo.h"


class FileRepository : public Repository {
protected:

	std::string file;
	virtual void loadFromFile() = 0;
	virtual void loadToFile() = 0;

public:

	FileRepository(const std::string& file) : Repository(), file(file) { }
	FileRepository(const FileRepository& other) : Repository(other), file(other.file) {}
	virtual ~FileRepository() = default;


	//FileRepository& operator=(const FileRepository& other);

	virtual void displayPets() = 0;


};