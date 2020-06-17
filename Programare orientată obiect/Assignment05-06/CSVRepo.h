#pragma once

#include "FileRepository.h"

class CSVRepository : public FileRepository {
protected:
	void loadFromFile() override;
	void loadToFile() override;

public:
	CSVRepository(const std::string &file);
	~CSVRepository();
	void displayPets() override;
};