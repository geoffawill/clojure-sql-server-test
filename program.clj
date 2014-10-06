;Compilation notes:
; The vsClojure installer should have stored the core framework directory in the VSCLOJURE_RUNTIMES_DIR environment variable pointing to C:\Users\[username]\AppData\Local\Microsoft\VisualStudio\[version]\Extensions\[randomCharacters]\Runtimes\

(ns program
  (:gen-class))

(System.Reflection.Assembly/LoadWithPartialName "System.Data")

;(defn -main [& args]
; )

(defn ms-get-from-server
  []
  (let [conn-str (str "Data Source=DEVEDW;Initial Catalog=DW_Sales;Integrated Security=SSPI;")
        conn (System.Data.SqlClient.SqlConnection. conn-str)
        _ (.Open conn)
        cmd (System.Data.SqlClient.SqlCommand.
              "select * from vw_dim_cust_shipto_current" conn)
        reader (.ExecuteReader cmd)]
    (while (.Read reader)
      (println (str "Customer Name: " (.GetString reader 5))))
    (.Close reader)
    (.Close conn)))