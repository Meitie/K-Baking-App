import 'package:app/controller/retrieve_quotes.dart';
import 'package:app/model/quotes.dart';
import 'package:app/page/quote_add.dart';
import 'package:app/widget/quote_card.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class QuoteView extends StatefulWidget {
  const QuoteView({Key? key}) : super(key: key);

  @override
  _QuoteViewState createState() => _QuoteViewState();
}

class _QuoteViewState extends State<QuoteView> {

  void initialDataLoad() {
    retrieveQuotes().then((newQuoteList) {
      Provider.of<Quotes>(context, listen: false).replaceList(newQuoteList);
    });
  }

  @override
  void initState() {
    super.initState();
    initialDataLoad();
  }

  @override
  Widget build(BuildContext context) {

    Quotes quotes = Provider.of<Quotes>(context);

    return Scaffold(
        backgroundColor: Colors.grey[200],
        appBar: AppBar(
          title: const Text('QuteQuote'),
          centerTitle: true,
        ),
        body: ListView.builder(
            itemCount: quotes.quoteList.length,
            itemBuilder: (context, index) {
              return QuoteCard(quote: quotes.quoteList.elementAt(index));
            }
        ),
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.add),
        backgroundColor: Colors.blue,
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const AddQuote()),
          );
        },
      ),
    );
  }

}