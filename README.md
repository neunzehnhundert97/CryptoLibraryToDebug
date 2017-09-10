# CryptoLibraryToDebug

This project shall help you programming by giving you not only the results but also the intermediate values of multiple cryptographic algorithms.
The code will not be optimized in any way except (hopefully) readability.

We do this mainly for fun, training, and because we experienced how difficult it can be
to implement a whole algorithm with only the final result as testing vectors.

Currently, this project is completely WIP.

## Usage

We will prepare releases with a compiled .jar-File as the project progresses. You may simply download this file and use it either via commandline or the GUI.

	java -jar CLTP.jar [OPTIONS]

## Available Algorithms

All algorithms listed here have been succesfully validated with the testcases in the package "test".

* Encryption
	* DES

* Hashing
	* MD2
	* MD5
	* SHA-1
	* SHA-2

## WIP Algorithms

* AES
* SHA-3

## Planned Algorithms

* Encryption
	* ...

* Hashing
	* ...

* Misc
	* Square-and-Multiply
	* Double-and-Add
	* Extended Euclidian Algorithm

## Sidenote

Feel free to use our code in your project, but we encourage you to rather write your own implementation.
Mainly because we did not spend any time in cleaning or optimizing it, but also because you benifit more
by doing stuff like this yourself.