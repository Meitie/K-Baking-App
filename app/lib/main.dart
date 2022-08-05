import 'package:app/model/quotes.dart';
import 'package:app/page/quote_view.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MultiProvider(
    providers: [
      ChangeNotifierProvider(create: (_) => Quotes()),
    ],
    child: const MyApp(),
  ));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'QuteQuotes',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const QuoteView(),
    );
  }
}