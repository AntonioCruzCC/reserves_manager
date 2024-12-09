(ns reserves-manager.persistance.reserve-ballance.schema)

(def schema [{:db/ident :reserve-ballance/reserve
              :db/valueType :db.type/ref
              :db/cardinality :db.cardinality/one
              :db/doc "Reference to the reserve"},
             {:db/ident :reserve-ballance/value
              :db/valueType :db.type/double
              :db/cardinality :db.cardinality/one
              :db/doc "The reserves ballance"},
             {:db/ident :reserve-ballance/timestamp
              :db/valueType :db.type/instant
              :db/cardinality :db.cardinality/one
              :db/doc "The date in which the reserve was set to the value"}])