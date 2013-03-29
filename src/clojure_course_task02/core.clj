(ns clojure-course-task02.core
  (:gen-class))

(import java.io.File)

(defn find-files [file-name path]
  (let [f (File. path)]
    (if (.isFile f)
      (if (not (empty? (re-seq (re-pattern file-name) (. f getName))))
        [(. f getName)]
        [])
      (reduce concat (doall (pmap (fn [s] (find-files file-name (str path (. File separator ) s)))
                                  (. f list)))))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path)))))
  (shutdown-agents))