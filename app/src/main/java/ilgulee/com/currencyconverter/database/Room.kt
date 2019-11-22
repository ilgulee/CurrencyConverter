package ilgulee.com.currencyconverter.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurrencyDatabaseDao {
    @Query("select * from currency_conversion_table")
    fun getCurrencyConversion(): LiveData<List<DatabaseCurrencyConversion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<DatabaseCurrencyConversion>)

    @Query("update currency_conversion_table set exchangeRate = :exchangeRate where id=:id")
    fun update(id: String, exchangeRate: Double)
}

@Database(entities = [DatabaseCurrencyConversion::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract val currencyDatabaseDao: CurrencyDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getInstance(context: Context): CurrencyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CurrencyDatabase::class.java,
                        "currency_conversion_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}