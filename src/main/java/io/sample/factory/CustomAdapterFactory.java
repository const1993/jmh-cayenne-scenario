package io.sample.factory;

import org.apache.cayenne.configuration.DataNodeDescriptor;
import org.apache.cayenne.configuration.server.DbAdapterDetector;
import org.apache.cayenne.configuration.server.DefaultDbAdapterFactory;
import org.apache.cayenne.dba.AutoAdapter;
import org.apache.cayenne.dba.DbAdapter;
import org.apache.cayenne.dba.PkGenerator;
import org.apache.cayenne.di.Inject;

import javax.sql.DataSource;
import java.util.List;

public class CustomAdapterFactory extends DefaultDbAdapterFactory {

    private PkGenerator pkGenerator;

    public CustomAdapterFactory(
            @Inject("cayenne.server.adapter_detectors")
                    List<DbAdapterDetector> detectors, @Inject() PkGenerator pkGenerator) {
        super(detectors);
        this.pkGenerator = pkGenerator;
    }

    @Override
    public DbAdapter createAdapter(
            DataNodeDescriptor nodeDescriptor,
            DataSource dataSource)  {

        AutoAdapter adapter =
                (AutoAdapter) super.createAdapter(nodeDescriptor, dataSource);

        // your PkGenerator goes here
        adapter.setPkGenerator(pkGenerator);
        return adapter;
    }
}

