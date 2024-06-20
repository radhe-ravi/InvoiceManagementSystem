# Invoice Management System 

## Problem Statement

The goal is to design and implement a database schema for an invoice management system. The system should be able to handle multiple users who can create invoices. Each invoice can have multiple items and can have multiple history entries for tracking changes and events. The following are the main entities involved in the system:

1. **User Detail**: This represents the users of the system who can create invoices and invoice items.
2. **Invoice**: This represents the invoices created by users.
3. **Invoice Item**: This represents the individual items listed in an invoice.
4. **Invoice History**: This represents the history of changes or events associated with an invoice.

The system should enforce the following relationships between these entities:

- Each user can create multiple invoices.
- Each invoice can contain multiple items.
- Each invoice can have multiple history entries.
- Each item and history entry should track the user who created it.

## Assumptions

1. **User Detail**:
   - Each user is uniquely identified by a UUID (`user_id`).
   - Users have a username, creation timestamp, and status.

2. **Invoice**:
   - Each invoice is uniquely identified by a UUID (`invoice_id`).
   - Each invoice is created by a user, referenced by the `created_by` foreign key.
   - Invoices have a unique invoice number, PAN number, GST number, and a creation timestamp.

3. **Invoice Item**:
   - Each invoice item is uniquely identified by a UUID (`invoice_item_id`).
   - Each item is associated with an invoice, referenced by the `invoice_id` foreign key.
   - Items have a name, description, and a creation timestamp.
   - Each item tracks the user who created it, referenced by the `created_by` foreign key.

4. **Invoice History**:
   - Each history entry is uniquely identified by a UUID (`invoice_history_id`).
   - Each history entry is associated with an invoice, referenced by the `invoice_id` foreign key.
   - History entries track events related to the invoice and have a creation timestamp.
   - Each history entry tracks the user who created it, referenced by the `created_by` foreign key.

## Database Relationships

### `user_detail` and `invoice`
- **Relationship Type**: One-to-Many
- **Details**: One `user_detail` can create multiple `invoices`.
- **Foreign Key**: `invoice.created_by` references `user_detail.user_id`

### `invoice` and `invoice_item`
- **Relationship Type**: One-to-Many
- **Details**: One `invoice` can have multiple `invoice_items`.
- **Foreign Key**: `invoice_item.invoice_id` references `invoice.invoice_id`

### `invoice` and `invoice_history`
- **Relationship Type**: One-to-Many
- **Details**: One `invoice` can have multiple `invoice_histories`.
- **Foreign Key**: `invoice_history.invoice_id` references `invoice.invoice_id`

### `user_detail` and `invoice_history`
- **Relationship Type**: One-to-Many
- **Details**: One `user_detail` can create multiple `invoice_histories`.
- **Foreign Key**: `invoice_history.created_by` references `user_detail.user_id`

### `user_detail` and `invoice_item`
- **Relationship Type**: One-to-Many
- **Details**: One `user_detail` can create multiple `invoice_items`.
- **Foreign Key**: `invoice_item.created_by` references `user_detail.user_id`

## Visual Representation
![img](pictures.jpg)

