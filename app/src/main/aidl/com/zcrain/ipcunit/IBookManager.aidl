// IBookManager.aidl
package com.zcrain.ipcunit;

import com.zcrain.ipcunit.domain.Book;

interface IBookManager {

   List<Book> getBookList();

   void addBook(in Book book);
}