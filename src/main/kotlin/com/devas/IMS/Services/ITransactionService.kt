package com.devas.IMS.Services

import com.devas.IMS.DTOs.Response
import com.devas.IMS.DTOs.TransactionRequest
import com.devas.IMS.Enums.TransactionStatus

interface ITransactionService {
    fun restockInventory(transactionRequest: TransactionRequest): Response
    fun sell(transactionRequest: TransactionRequest): Response
    fun returnToSupplier(transactionRequest: TransactionRequest): Response
    fun getAllTransactions(page: Int, size: Int, searchText: String?): Response
    fun getTransactionById(id: Long): Response
    fun getAllTransactionByMonthAndYear(month: Int, year: Int): Response
    fun updateTransactionStatus(transactionId: Long, transactionStatus: TransactionStatus): Response
}