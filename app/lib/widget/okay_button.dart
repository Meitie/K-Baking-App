import 'package:flutter/material.dart';

class OkayButton extends StatelessWidget {
  const OkayButton({Key? key}) : super(key: key);


  @override
  Widget build(BuildContext context) {

    return TextButton(
      child: const Text("OK"),
      onPressed: () {
        Navigator.pop(context);
      },
    );
  }
}