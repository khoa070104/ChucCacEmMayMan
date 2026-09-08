# Traditional Feast Order Management

Java console application for LAB211 assignment `J1.L.P0028`.

## Requirements

- JDK 21 or newer
- Apache Ant (or NetBeans)

## Run

```text
ant run
```

The application reads menu choices from `feastMenu.csv` and stores data in:

- `customers.dat`
- `feast_order_service.dat`

Dates are entered in `dd/MM/yyyy` format. Errors are written to `application.log`.

## Structure

- `model`: domain objects
- `repository`: CSV and binary persistence
- `service`: customer, menu, and order business rules
- `controller`: application workflow
- `view`: formatted console output
- `util`: validation, input, and logging
