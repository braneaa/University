#pragma once
#include <iostream>

template <class T>
class DynamicVector {
private:
	T* array;
	unsigned int size;
	unsigned int capacity;
	static const unsigned int DEFAUL_CAPACITY = 20;

	void resize(double factor = 2);

public:
	DynamicVector();
	DynamicVector(unsigned int initialCapacity);
	DynamicVector(const DynamicVector& v);
	~DynamicVector();

	T* getAll();
	T& operator [] (unsigned int pos);
	DynamicVector<T>& operator+(const T& classObj);
	void deleteElem(unsigned int pos);

	void add(const T &item);
	T* getAllElems() const;

	unsigned int getSize();

};




template<class T>
inline DynamicVector<T>::DynamicVector()
{
	capacity = DEFAUL_CAPACITY;
	size = 0;
	array = new T[DEFAUL_CAPACITY];

}

template<class T>
DynamicVector<T>::DynamicVector(unsigned int initialCapacity)
{
	array = new T[initialCapacity];
	size = 0;
	capacity = initialCapacity;
}

template<class T>
DynamicVector<T>::~DynamicVector()
{
	delete[] array;
}

template <class T>

unsigned int DynamicVector<T>::getSize() {
	return size;
}

template<class T>
T * DynamicVector<T>::getAll()
{
	return array;
}




template <class T>
T& DynamicVector<T>::operator [] (unsigned int index)
{
	return array[index]; 
}

template <class T>
DynamicVector<T>::DynamicVector(const DynamicVector& v)
{
	this->size = v.size;
	this->capacity = v.capacity;
	this->array = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		this->array[i] = v.array[i];
}

template <class T>
void DynamicVector<T>::resize(double factor) {
	this->capacity *= static_cast<int>(factor);

	T* els = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		els[i] = this->array[i];

	delete[] this->array;
	this->array = els;
}
template <class T>
void DynamicVector<T>::add(const T& e)
{
	if (this->size == this->capacity)
		this->resize();
	this->array[this->size] = e;
	this->size++;
}

template<class T>
T * DynamicVector<T>::getAllElems() const
{
	return this->array;
}


template <class T>
void DynamicVector<T>::deleteElem(unsigned int pos)
{
	if (pos < 0 || pos >= this->size)
		return;
	T* els = new T[this->capacity];
	for (int i = 0; i < pos; i++) {
		els[i] = this->array[i];
	}
	for (int i = pos; i < this->size; i++) {
		els[i] = this->array[i + 1];
	}
	delete[] this->array;
	this->array = els;
	this->size--;
}

template <class T>
DynamicVector<T>& DynamicVector<T>::operator+(const T& classObj) {
	this->add(classObj);
	return *this;
}
template <class T>
DynamicVector<T>& operator+(const T& classObj, DynamicVector<T>& s) {
	s.add(classObj);
	return s;
}
