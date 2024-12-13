(ns reserves-manager.persistance.reserve.schema)

(def schema [{:db/ident :reserve/name
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity
              :db/doc "The name of the reserve"}])