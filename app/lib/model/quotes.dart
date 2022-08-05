import 'package:app/model/quote.dart';
import 'package:flutter/material.dart';

class Quotes extends ChangeNotifier {
  List<Quote> quoteList = [];

  Quotes();

  void replaceList(List<Quote> newQuoteList)  {
    quoteList = newQuoteList;
    notifyListeners();
  }
}