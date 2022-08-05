import 'dart:convert';
import 'package:http/http.dart';
import 'package:app/model/quote.dart';

Future<List<Quote>> removeQuotes(int id) async {
  List<Quote> quotes = [];

  Response response = await delete(Uri.parse('http://192.168.0.101:5000/quote/$id'));
  List<dynamic> data = jsonDecode(response.body);

  for (var quote in data) {
    quotes.add(Quote(id: quote['id'], body: quote['body'], author: quote['author']));
  }

  return quotes;
}