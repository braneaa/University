#pragma once

#include "FileRepository.h"

class HTMLRepository : public FileRepository {
protected:
	void loadToFile() override;

public:
	HTMLRepository(const std::string &file);

	~HTMLRepository();

	void displayPets() override;
};