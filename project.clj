(defproject to-do-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [http-kit "2.3.0"]
                 [compojure "1.7.0"]
                 [prismatic/schema "1.4.1"]
                 [org.clojure/data.json "2.4.0"]
                 [ring/ring-json "0.5.1"]]
  
  :main to-do-clojure.server
  :repl-options {:init-ns to-do-clojure.server})
