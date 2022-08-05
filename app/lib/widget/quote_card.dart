import 'package:app/controller/remove_quote.dart';
import 'package:app/model/quotes.dart';
import 'package:flutter/material.dart';
import 'package:app/model/quote.dart';
import 'package:provider/provider.dart';

import 'okay_button.dart';

class QuoteCard extends StatelessWidget {
  final Quote quote;

  QuoteCard({required this.quote});

  @override
  Widget build(BuildContext context) {

    Future<void> showMyDialog(BuildContext context, Quote quote) {
      return showDialog<void>(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
              title: Text(quote.author),
              content: Text(quote.body),
              actions: const [
                OkayButton(),
              ],
          );
        },
      );
    }

    return InkWell(
      onTap: () => showMyDialog(context, quote),
      child: Card(
        margin: const EdgeInsets.fromLTRB(16, 16, 16, 0),
        child: Padding(
          padding: const EdgeInsets.all(12),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              Text(
                quote.body,
                style: TextStyle(
                  fontSize: 18,
                  color: Colors.grey[600],
                ),
              ),
              const SizedBox(height: 6),
              Text(
                quote.author,
                style: TextStyle(
                  fontSize: 14,
                  color: Colors.grey[800],
                ),
              ),
              TextButton.icon(
                onPressed: () {
                  removeQuotes(quote.id).then((newQuoteList) {
                    Provider.of<Quotes>(context, listen: false).replaceList(newQuoteList);
                  });
                },
                label: const Text('delete quote'),
                icon: const Icon(Icons.delete),
              )
            ],
          ),
        ),
      ),
    );
  }
}
