<?php
class Database 
{
    private $_Host;
    private $_User;
    private $_Password;
    private $_DataBaseName;
    private $_Connection;
 
    function Database($pHost, $pUser, $pPassword, $pDataBaseName) 
	{
        $this->_Host = $pHost;
        $this->_User = $pUser;
        $this->_Password = $pPassword; 
		$this->_DataBaseName = $pDataBaseName;   
        $this->connect();
		mysqli_set_charset($this->_Connection, "UTF8");
    }
	
	public function get_Connection()
	{
		return $this->_Connection;
	}
	
	public function doQuery($pQuery)
	{
		$result = mysqli_query($this->_Connection, $pQuery);
		if (!mysqli_error($this->_Connection))
		{
			return $this->createObject($result);
		}
		else
		{
			//return Array('Error' => mysqli_errno($this->_Connection), 'Mensaje'=> mysqli_error($this->_Connection));
			die(mysqli_error($this->_Connection));
		}
    }
	
	public function createObject($pQueryResult) 
	{
		$rows = array();
		while ($row = mysqli_fetch_object($pQueryResult)) 
		{
			$rows[] = $row;
		}
		return $rows;
	}
 
  	public function close() 
	{
        mysqli_close($this->_Connection);
    }
	
    private function connect() 
	{
        if (!$this->_Connection = mysqli_connect($this->_Host, $this->_User, $this->_Password, $this->_DataBaseName))
		{
            die("Could not create database connection");
        }
    }
}
?>