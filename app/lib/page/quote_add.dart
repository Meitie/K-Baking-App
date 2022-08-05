import 'package:app/controller/create_quote.dart';
import 'package:app/model/quote.dart';
import 'package:app/model/quotes.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class AddQuote extends StatefulWidget {
  const AddQuote({Key? key}) : super(key: key);

  @override
  _AddQuoteState createState() => _AddQuoteState();
}

class _AddQuoteState extends State<AddQuote> {
  TextEditingController bodyController = TextEditingController();
  TextEditingController authorController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('QuteQuote'),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[

            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 100, vertical: 5),
              child: TextFormField(
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Quote body'
                ),
                controller: bodyController,
              ),
            ),

            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 100, vertical: 5),
              child: TextFormField(
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Quote author'
                ),
                controller: authorController,
              ),
            ),

            Padding(
              padding: const EdgeInsets.symmetric(vertical: 10),
              child: TextButton(
                style: ButtonStyle(
                  backgroundColor: MaterialStateColor.resolveWith((states) => Colors.blue),
                ),
                onPressed: () {
                  createQuote(Quote(id: 0, body: bodyController.text, author: authorController.text)).then((newQuoteList) {
                    Provider.of<Quotes>(context, listen: false).replaceList(newQuoteList);
                  });
                  Navigator.pop(context);
                  },
                child: const Text(
                  'Add Quote',
                  style: TextStyle(
                    color: Colors.white,
                  ),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
