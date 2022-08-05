import 'dart:convert';
import 'package:http/http.dart';
import 'package:app/model/quote.dart';

Future<List<Quote>> createQuote(Quote quote) async {
  List<Quote> quotes = [];

  Response response = await post(Uri.parse('http://192.168.0.101:5000/quotes'),
      body: jsonEncode({
        'body': quote.body,
        'author': quote.author,
      }));

  List<dynamic> data = jsonDecode(response.body);

  for (var quote in data) {
    quotes.add(Quote(id: quote['id'], body: quote['body'], author: quote['author']));
  }

  return quotes;
}